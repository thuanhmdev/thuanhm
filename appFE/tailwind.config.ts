import type { Config } from "tailwindcss";

const config: Config = {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },

      colors: {
        background: "#0f1217",
      },
      keyframes: {
        blink: {
          "0%, 100%": { opacity: "1" },
          "50%": { opacity: "0" },
        },
      },
      animation: {
        blink: "blink 0.7s infinite",
      },
    },
    container: {
      center: true,
      screens: {
        sm: "640px",
        md: "768px",
        "2md": "900px",
        lg: "1024px",
        xl: "1280px",
        "2xl": "1400px",
        "3xl": "1600px",
      },
      padding: {
        DEFAULT: "1.2rem",
        sm: "1.5rem",
        md: "2.5rem",
        "2md": "3rem",
        lg: "3rem",
        xl: "3.5rem",
        "2xl": "5rem",
        "3xl": "6rem",
      },
    },
    screens: {
      sm: "640px",
      md: "768px",
      "2md": "900px",
      lg: "1024px",
      xl: "1280px",
      "2xl": "1400px",
      "3xl": "1600px",
    },
  },
  plugins: [],
};
export default config;
