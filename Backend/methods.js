const axios = require("axios").default
const API = require("./api");
const admin = require("firebase-admin");
const serviceAccount = require("./serviceAccount.json");

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
});
const db = admin.firestore();

const quoteURL = "https://finance.yahoo.com/quote"

const getTopGainerStocks = async (amt) => {
    const curr = amt / 72;
    const response = await axios.get(API.stocks);
    const data = response.data?.slice(0, 5).map((stock) => {
        return {
            ...stock,
            url: `${quoteURL}/${stock.ticker}`,
            amount: Number(curr / Number(stock.price)).toFixed(3)
        };
    })
    return data;
}

module.exports = {
    getUserData: async (id) => {
        const userRef = db.collection('users').doc(id);
        const getUser = async () => {
            const doc = await userRef.get();
            if (!doc.exists) {
                console.log('No such user!');
                return NaN;
            } else {
                return doc.data();
            }
        }

        const userData = await getUser();
        const amt = Number(userData.currentbal);
        if (amt <= 0) {
            return NaN;
        }
        return getTopGainerStocks(amt);
    }

};