"use client";
import { div } from "framer-motion/client";
import React, { useState, useEffect } from "react";

interface TypingEffectProps {
  text: string;
  className?: string;
}

const TypingEffect: React.FC<TypingEffectProps> = ({
  text,
  className = "",
}) => {
  const [displayText, setDisplayText] = useState("");
  const [isTyping, setIsTyping] = useState(true);

  useEffect(() => {
    let i = 0;
    let timer: NodeJS.Timeout;

    const typeWriter = () => {
      if (i < text.length) {
        setDisplayText(text.substring(0, i + 1));
        i++;
        timer = setTimeout(typeWriter, 100);
      } else {
        setIsTyping(false);
        timer = setTimeout(eraseText, 2000);
      }
    };

    const eraseText = () => {
      if (i > 0) {
        setDisplayText(text.substring(0, i - 1));
        i--;
        timer = setTimeout(eraseText, 50);
      } else {
        setIsTyping(true);
        timer = setTimeout(typeWriter, 1000);
      }
    };

    typeWriter();

    return () => clearTimeout(timer);
  }, [text]);

  return (
    <div className={`flex gap-x-4 ${className} `}>
      <p className="text-neutral-300">{"I'M"} </p>
      <p
        className={`
      ${isTyping ? "animate-cursor-blink" : ""}
    `}
      >
        {displayText}
      </p>
    </div>
  );
};

export default TypingEffect;
