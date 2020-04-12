const express = require('express')
const db = require('../db/dbqueries')
const router = express.Router()

router.get('/login', async (req, res, next)=>{
    try {
      if(!req.headers.authorization || req.headers.authorization.indexOf('Basic') === -1){
        return res.status(401).json({message:'Missing Authorization Header'})
      }
      const base64Credebtials = req.headers.authorization.split(' ')[1]
      const credentials = Buffer.from(base64Credebtials, 'base64').toString('ascii')
      const [username, password] = credentials.split(':')
      
      let login = await db.login({username, password})
      if(login['login']==='failed'){
        return res.status(401).json({message:'Invalid Authentication Credentials'})
      } else{
        res.json(login)
        }
      } catch (error) {
        console.log(error)
        res.sendStatus(500)
      }
})

router.get('/account', async (req, res, next)=>{
try {
  let accountQuery = await db.getAccount(req.query)
  if(accountQuery['account'] === 'does not exist'){
    return res.status(401).json({message:'does not exist'})
  }
  else{
    res.json(accountQuery)
  }
} catch (error) {
  console.log(error)
        res.sendStatus(500)
}
})


router.get('/profile', async (req, res, next)=>{
  try {
    let accountQuery = await db.getProfile(req.query)
    if(accountQuery['profile'] === 'does not exist'){
      return res.status(401).json({message:'does not exist'})
    }
    else{
      res.json(accountQuery)
    }
  } catch (error) {
    console.log(error)
          res.sendStatus(500)
  }
})

router.get('/water', async (req, res, next)=>{
  try {
    let waterQuery = await db.getWaterReadings(req.query)
    if(waterQuery['account'] === 'does not exist'){
      return res.status(401).json({message:'does not exist'})
    }
    else{
      res.json(waterQuery)
    }
  } catch (error) {
    console.log(error)
          res.sendStatus(500)
  }
})



module.exports = router
