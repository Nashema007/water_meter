const express = require('express')
const db = require('../db/dbqueries')
const router = express.Router()

router.get('/login', async (req, res, next)=>{
    try {
        let login = await db.login(req.query)
        res.json(login)
        console.log(login)
      } catch (error) {
        console.log(error)
        res.sendStatus(500)
      }
})


module.exports = router
