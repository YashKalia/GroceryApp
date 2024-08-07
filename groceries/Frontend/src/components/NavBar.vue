<template>
    <div class="wrapper">
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <RouterLink class="navbar-brand" to="/">GroceryApp</RouterLink>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <RouterLink class="nav-link active" aria-current="page" to="/customize">Customize</RouterLink>
        </li>
      </ul>
    </div>
  </div>
</nav>
    </div>
</template>

<script>
export default {
  name: 'NavBar'
}
</script>