let observers =[]

function attach(observer){
    observers.push(observer)
}

function detach(observer){
    let newObserver = []
    for (let i of observers){
        if (i != observer){
            newObserver.push(1)
        }
    }
    observer = newObserver
}

function notify(){
    for (let i of observers){
        console.log(i+": Notified")
    }
}


attach("A")
attach("B")
notify()
attach("c")
detach("B")
notify()