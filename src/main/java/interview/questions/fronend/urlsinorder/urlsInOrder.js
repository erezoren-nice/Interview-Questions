
let urls = ["http://1", "http://2", "http://3", "http://4", "http://5"];

const handleUrls = (urls = []) => {
  let printHandlerClosure = printHandler(urls.length);
  printHandlerClosure.start();
  urls.map((url, idx) => {
    invoke(url).then((resp) => {
      printHandlerClosure.addResolution(idx, resp);
    })
  });
}
const invoke = async (url) => {
  let randomWait =  Math.floor(Math.random() * (10000 - 1000)) + 1000;
  return new Promise((resolve, reject) => {
    try {
      setTimeout(() => {
        resolve(`Invoked ${url} - ${new Date().toLocaleTimeString()}, waits ${randomWait} millis`)
      }, randomWait)
    } catch (err) {
      reject("Error")
    }

  });
}

const printHandler = (length) => {
  let resolutions = [];
  let idx = 0; 
  return {
    start: () => {
      let interval = setInterval(()=>{
        if(resolutions[idx]){
          console.log(resolutions[idx])
          idx++
        }
        if(idx===length){
         clearInterval(interval);
        }
      },1)
    },
    addResolution: (idx, resolution) => {
      resolutions[idx] = resolution;
    }
  }
}

handleUrls(urls)