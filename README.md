# GroceryApp
Made with Java and Vue.js

## Backend Setup

### Prerequisites

It is assumed that you have Java installed on your system. The project 
was created with OpenJDK 22.0.2

Create a .env file in resources folder in the backend project and 
copy the contents of the .env.example file into the .env file.

Then replace the actual values with the secrets.

Then go to src/main/java/dev/yashkalia/groceries/GroceriesApplication.java and run the main method.

The backend should now be running on port 8080.

## Install Node via NVM

Go to https://github.com/coreybutler/nvm-windows/releases and download the nvm-setup.exe file.

Once installed open the groceries/Frontend folder in Visual Studio Code and run in the integrated terminal
```sh
nvm install 20.16.0
```

Then run 

```sh
nvm list
```

to confirm the installed version of node.

Then run

```sh
npm i
```

to install the dependencies. You should now see a node modules folder in the Frontend folder.

Then run

```sh
npm run dev
```

to start the frontend.
Navigate to the localhost address mentioned in the terminal to see the frontend.

To run unit tests

```sh
npm run test
```

See Package.json or NPM Scripts section for full set of scripts


