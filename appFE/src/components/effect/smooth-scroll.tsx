"use client";
import React, { useEffect, useRef, useState } from "react";

const SmoothScroll: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [speed, setSpeed] = useState(0);
  const lastUpdateTimeRef = useRef(0);
  const requestRef = useRef<number>();
  const slowdownFactor = 0.3;
  const easingFactor = 0.9;

  useEffect(() => {
    const handleWheel = (ev: WheelEvent) => {
      const direction = ev.deltaY > 0 ? 1 : -1;
      const factor = direction > 0 ? slowdownFactor : slowdownFactor * 1.2; // Tăng cho lăn lên
      setSpeed(ev.deltaY * factor);
      ev.preventDefault();
    };

    const update = (currentTime: number) => {
      setSpeed((prevSpeed) => {
        const direction = prevSpeed > 0 ? 1 : -1;
        const factor = direction > 0 ? easingFactor : easingFactor * 0.9; // Giảm cho lăn lên
        return prevSpeed * factor;
      });
      window.scrollBy(0, speed);
      lastUpdateTimeRef.current = currentTime;
      requestRef.current = requestAnimationFrame(update);
    };

    requestRef.current = requestAnimationFrame(update);
    window.addEventListener("wheel", handleWheel, { passive: false });

    return () => {
      if (requestRef.current) {
        cancelAnimationFrame(requestRef.current);
      }
      window.removeEventListener("wheel", handleWheel);
    };
  }, [speed]);

  return <>{children}</>;
};

export default SmoothScroll;
