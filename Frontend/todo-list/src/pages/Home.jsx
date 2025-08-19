import { Link } from "react-router-dom";
export default function Home() {
  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-100">
      <h1 className="text-3xl font-bold">Dashboard</h1>
      <Link
        to="/login"
        className="hover:underline hover:decoration-red-500 hover:text-red-500"
      >
        Login
      </Link>
      <Link
        to="/register"
        className="hover:underline hover:decoration-red-500 hover:text-red-500"
      >
        Register
      </Link>
    </div>
  );
}
