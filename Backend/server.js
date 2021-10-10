require('dotenv').config();
const express = require("express");
const cors = require("cors");
const app = express();
const { getUserStocksData, getUserData } = require('./methods')

const PORT = process.env.PORT || 5000;

app.use(cors());

app.use(express.urlencoded({
    extended: false
}));
app.use(express.json());

app.get("/investments", async (req, res) => {
    const userID = req.query.id;
    const data = await getUserStocksData(userID);
    console.log(data);
    if (Number.isNaN(data)) {
        res.send({
            error: "Not sufficient funds to invest in stocks"
        })
    }
    res.send(data)
});

app.get("/user", async (req, res) => {
    const userID = req.query.id;
    const user = await getUserData(userID);
    res.send(user);
})

app.listen(PORT, () => console.log("Server is running..."));