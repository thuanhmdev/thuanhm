"use client";
import React, { useEffect, useRef, useState } from "react";

const MouseLight = () => {
  const lightRef = useRef<HTMLDivElement>(null);
  const targetRef = useRef({ x: 0, y: 0 });
  const [position, setPosition] = useState({ x: 0, y: 0 });

  const handleMoveMouse = (e: MouseEvent) => {
    targetRef.current = { x: e.clientX, y: e.clientY };
  };

  useEffect(() => {
    window.addEventListener("mousemove", handleMoveMouse);
    return () => {
      window.removeEventListener("mousemove", handleMoveMouse);
    };
  }, []);

  useEffect(() => {
    let animationFrameId: number;
    const animationLightMouse = () => {
      setPosition((prevPos) => {
        const newX = prevPos.x + (targetRef.current.x - prevPos.x) * 0.1;
        const newY = prevPos.y + (targetRef.current.y - prevPos.y) * 0.1;
        return { x: newX, y: newY };
      });
      animationFrameId = requestAnimationFrame(animationLightMouse);
    };

    animationLightMouse();

    return () => {
      cancelAnimationFrame(animationFrameId);
    };
  }, []);

  return (
    <div
      ref={lightRef}
      className="fixed 2xl:w-[650px] 2xl:h-[650px] 3xl:w-[700px] 3xl:h-[700px] rounded-lg pointer-events-none z-50"
      style={{
        left: `${position.x}px`,
        top: `${position.y}px`,
        transform: "translate(-50%, -50%)",
        background: `radial-gradient(ellipse at center, rgba(0, 145, 227, 0.2), transparent 70%)`,
      }}
    />
  );
};

export default MouseLight;
