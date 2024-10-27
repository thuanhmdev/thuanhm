"use client";
import Image from "next/image";
import React, { useEffect, useRef, useState } from "react";

interface ParallaxImageProps {
  src: string;
  alt: string;
  width: number;
  height: number;
  reverse?: boolean;
}

const ParallaxImage: React.FC<ParallaxImageProps> = ({
  alt,
  width = 60,
  height = 60,
  src,
  reverse = false,
}) => {
  const imgRef = useRef<HTMLImageElement | null>(null);
  const [lastScrollTop, setLastScrollTop] = useState<number>(0);

  useEffect(() => {
    const handleScroll = (): void => {
      const scrollTop: number =
        window.pageYOffset || document.documentElement.scrollTop;
      if (imgRef.current) {
        if (scrollTop > lastScrollTop) {
          // Cuộn xuống
          imgRef.current.style.transform = `translateY(${
            reverse ? `-8px` : "8px"
          })`;
        } else {
          // Cuộn lên
          imgRef.current.style.transform = `translateY(${
            reverse ? `8px` : "-8px"
          })`;
        }
        setLastScrollTop(scrollTop <= 0 ? 0 : scrollTop);

        // Reset vị trí sau 300ms
        setTimeout(() => {
          if (imgRef.current) {
            imgRef.current.style.transform = "translateY(0)";
          }
        }, 300);
      }
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, [lastScrollTop]);

  return (
    <Image
      src={src}
      ref={imgRef}
      alt={alt}
      height={height}
      width={width}
      style={{
        transition: "transform 0.3s ease",
      }}
    />
  );
};

export default ParallaxImage;
