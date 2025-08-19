import { Link } from "react-router-dom";
import { useState } from "react";
export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const passwordPattern =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@_()]{8,24}$/;
  const usernamePattern = /^[A-Za-z][A-Za-z0-9]{3,19}$/;
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!passwordPattern.test(password)) {
      setError(
        "Password must be 8-24 characters, include uppercase, lowercase, a number, and may contain @, _, (, )"
      );
      return;
    }

    if (!usernamePattern.test(username)) {
      setError(
        "Username must be 4-20 characters long, start with a letter, and contain only letters and numbers"
      );
      return;
    }

    setError("");
    console.log("Register success");
  };

  return (
    <div
      className="flex justify-center items-center min-h-screen"
      style={{
        backgroundImage: "url('images/background.png')",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div
        id="wrapper"
        className="border w-100 p-6 rounded-lg shadow-md backdrop-blur-2xl"
      >
        <form action="">
          <h1 className=" flex text-xl font-bold mb-6 items-center justify-center">
            Login
          </h1>
          <div id="input-box" className="border-b-2 mb-4">
            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
              className="w-full py-2 px-3"
            />
          </div>
          <div id="input-box" className="border-b-2 mb-4">
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              className="w-full py-2 px-3"
            />
          </div>
          <div
            id="remember-forgot"
            className="flex justify-between items-center mb-4"
          >
            <label>
              <input type="checkbox" /> Remember me
            </label>
            <Link
              to="/ForgetPass"
              className="hover:underline hover:decoration-red-500 hover:text-red-500"
            >
              Forgot password?
            </Link>
          </div>
          <button
            type="submit"
            id="btn"
            className="flex w-full justify-center border-2 border-blue-500 text-blue-500 py-2 px-4 rounded-lg hover:bg-blue-500 hover:text-white"
          >
            Login
          </button>
          <div id="register-link" className="flex justify-center mt-5">
            <p>
              Don't have an account?{" "}
              <Link
                to="/Register"
                className="hover:underline hover:decoration-red-500 hover:text-red-500"
              >
                Register
              </Link>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
}
