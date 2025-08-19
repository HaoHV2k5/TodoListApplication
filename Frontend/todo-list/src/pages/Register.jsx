import { useState } from "react";

export default function Register() {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirm, setConfirm] = useState("");
  const [error, setError] = useState("");

  const passwordPattern =
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@_()]{8,24}$/;
  const usernamePattern = /^[A-Za-z][A-Za-z0-9]{3,19}$/;
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const handleSubmit = (e) => {
    e.preventDefault();

    if (password !== confirm) {
      setError("Passwords do not match!");
      return;
    }

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

    if (!emailPattern.test(email)) {
      setError("Please enter a valid email address");
      return;
    }

    setError("");
    console.log("Register success");
  };

  return (
    <div className="flex justify-center items-center min-h-screen">
      <div id="wrapper" className="border w-100 p-6 rounded-lg shadow-md">
        <form onSubmit={handleSubmit}>
          <h1 className="flex text-xl font-bold mb-6 items-center justify-center">
            Register
          </h1>

          {error && <p className="text-red-500 text-center">{error}</p>}

          <div className="border-b-2 mb-4">
            <input
              type="text"
              placeholder="Username"
              onChange={(e) => setUsername(e.target.value)}
              required
              className="w-full py-2 px-3"
            />
          </div>

          <div className="border-b-2 mb-4">
            <input
              type="email"
              placeholder="Email"
              onChange={(e) => setEmail(e.target.value)}
              required
              className="w-full py-2 px-3"
            />
          </div>

          <div className="border-b-2 mb-4">
            <input
              type="password"
              placeholder="Password"
              pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d@_()]{8,24}$"
              title="Password must be 8-24 characters, include uppercase, lowercase, a number, and may contain @, _, (, )"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full py-2 px-3"
            />
          </div>

          <div className="border-b-2 mb-4">
            <input
              type="password"
              placeholder="Confirm Password"
              required
              value={confirm}
              onChange={(e) => setConfirm(e.target.value)}
              className="w-full py-2 px-3"
            />
          </div>

          <button
            type="submit"
            className="flex w-full justify-center border-2 border-blue-500 text-blue-500 py-2 px-4 rounded-lg hover:bg-blue-500 hover:text-white"
          >
            Register
          </button>
        </form>
      </div>
    </div>
  );
}
