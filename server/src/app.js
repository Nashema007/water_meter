

const express = require('express')
const router = require('./routes/routes')
const morgan = require('morgan')
const app = express()
const PORT = process.env.PORT || '3000'

app.use(express.json())
app.use(morgan('short'))
app.use('/parisymptoms/api', router)

app.listen(PORT, () => {
  console.log(`Server is listening on port: ${PORT}`)
})
