const pool = require('./dbconn')

let watermeterdb = {}

watermeterdb.login = (user)=>{
    return new Promise((resolve, reject)=>{
        pool.query('Select * FROM user WHERE username = ? and password = ?', 
        [user['username'], user['password']], (err, results)=>{
            if (err) {
                return reject(err)
              }
              if (Object.keys(results).length === 0) {
                return resolve({ 'login': 'failed' })
              } else {
                return resolve({
                  'id':results[0].id,
                  'username':results[0].username,
                  'email':results[0].email
                })
              }
        })
    })
}

watermeterdb.getAccount = (user)=>{
  return new Promise((resolve, reject)=>{
    pool.query('Select * FROM account where username = ?', 
    user['username'],(err, results)=>{
      if(err){
        return reject(err)
      }
      if (Object.keys(results).length === 0) {
        return resolve({ 'account': 'does not exist' })
      } else {
        return resolve({
          'id':results[0].id,
          'username':results[0].username,
          'accountNumber':results[0].accountNumber,
          'currentBalance':results[0].currentBalance,
          'previousBalance':results[0].previousBalance,
          'dueDate':results[0].dueDate,
          'taxInvoiceNumber':results[0].taxInvoiceNumber
        })
      }
    } )
  })
}


watermeterdb.getProfile = (user)=>{
  return new Promise((resolve, reject)=>{
    pool.query('Select * FROM userdetails where username = ?', 
    user['username'],(err, results)=>{
      if(err){
        return reject(err)
      }
      if (Object.keys(results).length === 0) {
        return resolve({ 'profile': 'does not exist' })
      } else {
        return resolve({
          'id':results[0].id,
          'username':results[0].username,
          'accountNumber':results[0].accountNumber,
          'name':results[0].name,
          'address':results[0].address,
          'nationalId':results[0].nationalId
        })
      }
    } )
  })
}

watermeterdb.getWaterReadings = (user)=>{
  return new Promise((resolve, reject)=>{
    pool.query('Select * FROM waterReadings where accountNumber=?', 
    user['accountNumber'],(err, results)=>{
      if(err){
        return reject(err)
      }
      if (Object.keys(results).length === 0) {
        return resolve({ 'account': 'does not exist' })
      } else {
        return resolve(results)
      }
    } )
  })
}



module.exports = watermeterdb