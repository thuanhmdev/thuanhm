"use client";
import React from "react";
import { motion } from "framer-motion";

function RevealEffect({
  children,
  typeEffect = "BOTTOTOP",
}: {
  children: React.ReactNode;
  typeEffect?: "FADEIN" | "BOTTOTOP";
}) {
  switch (typeEffect) {
    case "FADEIN":
      return (
        <motion.div
          initial={{ opacity: 0 }}
          whileInView={{
            opacity: 1,
            transition: { duration: 2, ease: "easeInOut" },
          }}
        >
          {children}
        </motion.div>
      );
    default:
      return (
        <motion.div
          initial={{ opacity: 0, y: -50 }}
          whileInView={{ opacity: 1, y: 0, transition: { duration: 1 } }}
        >
          {children}
        </motion.div>
      );
  }
}

export default RevealEffect;
