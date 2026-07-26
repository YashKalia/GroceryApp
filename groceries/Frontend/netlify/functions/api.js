const seedItems = () => [
  {
    id: '1',
    itemName: 'Heineken',
    price: 2.5,
    itemType: 'Beer',
    age: 0,
    discountId: '2',
    isPack: false,
    weight: 0,
    productImage:
      'https://images.unsplash.com/photo-1608270586620-248524c67de9?w=400',
  },
  {
    id: '2',
    itemName: 'Heineken 6-pack',
    price: 12,
    itemType: 'Beer',
    age: 0,
    discountId: 'beer-discount',
    isPack: true,
    weight: 0,
    productImage:
      'https://images.unsplash.com/photo-1618885472179-5e474019f2a9?w=400',
  },
  {
    id: '3',
    itemName: 'Sourdough',
    price: 3.2,
    itemType: 'Bread',
    age: 1,
    discountId: 'bread-discount',
    isPack: false,
    weight: 0,
    productImage:
      'https://images.unsplash.com/photo-1549931319-a545dcf3bc73?w=400',
  },
  {
    id: '4',
    itemName: 'Whole Wheat',
    price: 2.8,
    itemType: 'Bread',
    age: 3,
    discountId: 'bread-discount',
    isPack: false,
    weight: 0,
    productImage:
      'https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400',
  },
  {
    id: '5',
    itemName: 'Tomatoes',
    price: 1.5,
    itemType: 'Vegetable',
    age: 0,
    discountId: 'veg-discount',
    isPack: false,
    weight: 250,
    productImage:
      'https://images.unsplash.com/photo-1546470427-e212b7d31075?w=400',
  },
  {
    id: '6',
    itemName: 'Carrots',
    price: 1.2,
    itemType: 'Vegetable',
    age: 0,
    discountId: 'veg-discount',
    isPack: false,
    weight: 400,
    productImage:
      'https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=400',
  },
];

const discounts = [
  {
    id: 'beer-discount',
    type: 'BeerDiscount',
    sixPackDiscountPrice: 10,
  },
  {
    id: 'bread-discount',
    type: 'BreadDiscount',
    Age: 3,
    Buy: 2,
    Take: 1,
  },
  {
    id: 'veg-discount',
    type: 'VegetableDiscount',
    weightLowerLimit: 100,
    weightUpperLimit: 500,
    discountPercentage: 10,
  },
];

// Ephemeral store for this function instance
const globalStore = globalThis.__groceryStore || {
  items: seedItems(),
};
globalThis.__groceryStore = globalStore;

const corsHeaders = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Headers': 'Content-Type',
  'Access-Control-Allow-Methods': 'GET,POST,OPTIONS',
  'Content-Type': 'application/json',
};

function json(statusCode, body) {
  return {
    statusCode,
    headers: corsHeaders,
    body: JSON.stringify(body),
  };
}

function checkout(cartItems) {
  const receiptItems = [];
  let totalOrderPrice = 0;

  for (const cartItem of cartItems || []) {
    const type = cartItem.props.itemType;
    if (type === 'Beer') {
      const total = beerTotal(cartItem);
      totalOrderPrice += total;
      receiptItems.push({ cartItem, totalPrice: total });
    } else if (type === 'Bread') {
      const take = breadTakeCount(cartItem);
      const total = cartItem.itemCount * cartItem.props.price;
      totalOrderPrice += total;
      receiptItems.push({
        cartItem: { ...cartItem, itemCount: take },
        totalPrice: total,
      });
    } else if (type === 'Vegetable') {
      const pct = vegetableDiscountPct(cartItem);
      const before = cartItem.itemCount * cartItem.props.price;
      const total = before - (pct / 100) * before;
      totalOrderPrice += total;
      receiptItems.push({ cartItem, totalPrice: total });
    }
  }

  return { receiptItems, totalOrderPrice };
}

function beerTotal(cartItem) {
  if (cartItem.props.isPack) {
    return cartItem.itemCount * cartItem.props.price;
  }
  const sixPackCount = Math.floor(cartItem.itemCount / 6);
  const remaining = cartItem.itemCount % 6;
  const sixPack = globalStore.items.find((i) => i.id === cartItem.props.discountId);
  if (sixPack) {
    return sixPackCount * sixPack.price + remaining * cartItem.props.price;
  }
  return cartItem.itemCount * cartItem.props.price;
}

function breadTakeCount(cartItem) {
  const breadDiscount = discounts.find(
    (d) => d.type === 'BreadDiscount' && d.Age === cartItem.props.age
  );
  if (!breadDiscount || cartItem.itemCount < breadDiscount.Buy) {
    return cartItem.itemCount;
  }
  const applicable = Math.floor(cartItem.itemCount / breadDiscount.Buy);
  return cartItem.itemCount + applicable * breadDiscount.Take;
}

function vegetableDiscountPct(cartItem) {
  const totalWeight = cartItem.itemCount * cartItem.props.weight;
  const veg = discounts.find(
    (d) =>
      d.type === 'VegetableDiscount' &&
      d.weightLowerLimit < totalWeight &&
      (d.weightUpperLimit === -1 || d.weightUpperLimit >= totalWeight)
  );
  return veg ? veg.discountPercentage : 0;
}

exports.handler = async (event) => {
  if (event.httpMethod === 'OPTIONS') {
    return { statusCode: 204, headers: corsHeaders, body: '' };
  }

  const path = (event.path || '').replace(/\/$/, '');

  try {
    if (event.httpMethod === 'GET' && path.endsWith('/items')) {
      return json(200, globalStore.items);
    }

    if (event.httpMethod === 'POST' && path.endsWith('/checkout')) {
      const cartItems = JSON.parse(event.body || '[]');
      return json(200, checkout(cartItems));
    }

    if (event.httpMethod === 'POST' && path.endsWith('/updateItem')) {
      const { id, price } = JSON.parse(event.body || '{}');
      const item = globalStore.items.find((i) => i.id === id);
      if (!item) return json(404, { error: 'Item not found' });
      item.price = Number(price);
      return json(200, 1);
    }

    return json(404, { error: 'Not found', path });
  } catch (error) {
    return json(500, { error: error.message || 'Server error' });
  }
};
