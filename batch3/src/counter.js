export default function Counter(){
    let count = 0;

    function Increment(){
        count++
    }
    function Decrement(){
        count--
    }
    function Reset(){
        count=0
    }
    return(
        <>
        <h1>{count}</h1>
        <button onClick={Increment}>Increment</button>
        <button onClick={Decrement}>Decrement</button>
        <button onClick={Reset}> Reset</button>
        </>
    )
}