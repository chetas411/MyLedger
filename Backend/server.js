require('dotenv').config();
const express = require("express");
const axios = require("axios");
const cors = require("cors");
const app = express();

const PORT = process.env.PORT || 5000;

app.use(cors());

app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());

// Stocks API credential
const STOCK_API_KEY = process.env.STOCK_API_KEY;


app.listen(PORT, () => console.log("Server is running..."));