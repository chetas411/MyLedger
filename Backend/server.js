require('dotenv').config();
const express = require("express");
const axios = require("axios");
const cors = require("cors");
const API  = require("./api");
const admin = require("firebase-admin")
const app = express();

const serviceAccount = require("./serviceAccount.json");

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});

const db = admin.firestore();

const PORT = process.env.PORT || 5000;

app.use(cors());

app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());

// console.log(API.stocks);

console.log(db);

app.listen(PORT, () => console.log("Server is running..."));