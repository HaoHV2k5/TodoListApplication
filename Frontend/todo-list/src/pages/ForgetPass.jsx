export default function ForgetPass() {
  return (
    <div className="flex justify-center items-center min-h-screen"> 
      <div id="wrapper" className="border w-100 p-6 rounded-lg shadow-md">
        <form action="">
          <h1 className=" flex text-xl font-bold mb-6 items-center justify-center">
            Forgot Password
          </h1>
          <div id="input-box" className="border-b-2 mb-4">
            <input
              type="email"
              placeholder="Email"
              required
              className="w-full py-2 px-3"
            />
          </div>
          <button
            type="submit"
            id="btn"
            className="flex w-full justify-center border-2 border-blue-500 text-blue-500 py-2 px-4 rounded-lg hover:bg-blue-500 hover:text-white"
          >
            Reset Password
          </button>
        </form>
      </div>
    </div>
  );
}
