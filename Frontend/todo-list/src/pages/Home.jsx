import { useEffect } from "react";
import { Link } from "react-router-dom";

export default function Home() {
  useEffect(() => {
    const sections = document.querySelectorAll(".fade-in");

    const observer = new IntersectionObserver(
      (entries) => {
        entries.forEach((entry) => {
          if (entry.isIntersecting) {
            entry.target.classList.add("show");
          }
        });
      },
      { threshold: 0.4 }
    );

    sections.forEach((section) => observer.observe(section));

    return () => observer.disconnect();
  }, []);

  return (
    <div className="min-h-screen flex flex-col bg-gradient-to-br from-cyan-700 to-cyan-300 text-gray-900">
      {/* Navbar */}
      <header className="flex justify-between items-center px-6 py-4 bg-white/30 backdrop-blur-md rounded-b-xl shadow">
        <h1 className="text-lg font-bold text-white">Study Land</h1>
        <div className="flex gap-4">
          <Link
            to="/login"
            className="px-4 py-2 rounded-lg bg-gradient-to-r from-cyan-300 to-cyan-700 text-white font-semibold shadow hover:from-cyan-700 hover:to-cyan-300 transition"
          >
            Login
          </Link>
          <Link
            to="/register"
            className="px-4 py-2 rounded-lg bg-gradient-to-r from-cyan-300 to-cyan-700 text-white font-semibold shadow hover:from-cyan-700 hover:to-cyan-300 transition"
          >
            Register
          </Link>
        </div>
      </header>

      {/* Hero Section */}
      <main className="flex flex-col items-center text-center px-6 py-20 text-white">
        <h2 className="text-4xl font-bold mb-4">Study Land</h2>
        <p className="max-w-xl text-lg opacity-90">
          Ứng dụng giúp bạn đặt thời gian học tập, tập trung và nghỉ ngơi hợp lý
          để nâng cao hiệu quả học tập mỗi ngày.
        </p>
      </main>

      {/* Alternating Sections */}
      <section className="fade-in flex items-center justify-between gap-10 px-10 py-12 bg-white/20 backdrop-blur-lg rounded-2xl mx-8 my-8 shadow-lg">
        <div className="flex-shrink-0">
          <img
            src="https://media1.thehungryjpeg.com/thumbs2/ori_3633029_nymtdrfklj4muj0rc7030zh5ac0n6ywm1cdzhuya_calendar-icon-flat.png"
            alt="study"
            className="w-80 rounded-xl shadow-lg transform transition hover:scale-105 hover:brightness-90"
          />
        </div>
        <div className="max-w-lg text-white">
          <h3 className="text-2xl font-semibold mb-3">Calendar</h3>
          <p className="text-base leading-relaxed">
            Giúp người dùng quản lý lịch hẹn, sự kiện ngắn hạn.
          </p>
        </div>
      </section>

      <section className="fade-in flex flex-row-reverse items-center justify-between gap-10 px-10 py-12 bg-white/20 backdrop-blur-lg rounded-2xl mx-8 my-8 shadow-lg">
        <div className="flex-shrink-0">
          <img
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSa8vxEhGraMgy4XON5cBalK468aeYzqMiPaA&s"
            alt="focus"
            className="w-80 rounded-xl shadow-lg transform transition hover:scale-105 hover:brightness-90"
          />
        </div>
        <div className="max-w-lg text-white">
          <h3 className="text-2xl font-semibold mb-3">Checklist</h3>
          <p className="text-base leading-relaxed">
            Giúp người dùng quản lý công việc theo từng trạng thái.
          </p>
        </div>
      </section>

      {/* Footer */}
      <footer className="mt-auto py-4 text-center text-sm text-white bg-black/30 rounded-t-xl">
        © 2025 Study Land. All rights reserved.
      </footer>

      {/* Fade-in hiệu ứng custom (dùng Tailwind + thêm CSS nhỏ trong index.css hoặc globals.css) */}
      <style>{`
        .fade-in {
          opacity: 0;
          transform: translateY(40px);
          transition: all 0.8s ease-out;
        }
        .fade-in.show {
          opacity: 1;
          transform: translateY(0);
        }
      `}</style>
    </div>
  );
}
