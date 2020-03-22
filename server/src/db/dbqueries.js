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
                return resolve({ 'login': 'successful' })
              }
        })
    })
}


module.exports = watermeterdb