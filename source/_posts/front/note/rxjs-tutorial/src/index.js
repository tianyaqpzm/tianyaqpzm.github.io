import { fromEvent, from } from "rxjs"
import { concatMap } from "rxjs/operators"
import axios from "axios"

const button = document.getElementById("btn")

fromEvent(button, "click")
  .pipe(
    concatMap(event => from(axios.get("http://localhost:3005/token"))),
    concatMap(token => from(axios.get("http://localhost:3005/userInfo")))
  )
  .subscribe(console.log)

from(['a','b','c']).subscribe(console.log)


function p(){
  return new Promise(function(resolve) {
    setTimeout(function(){
      resolve({a:1})
    }, 2000)
  })
}
from(p()).subscribe(console.log)

