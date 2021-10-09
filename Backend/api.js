require('dotenv').config();

// Stocks API credential
const STOCK_API_KEY = process.env.STOCK_API_KEY;

const API = {
    stocks: `https://financialmodelingprep.com/api/v3/gainers?apikey=${STOCK_API_KEY}`
}

module.exports = API;