import { Dot } from "lucide-react";
import React from "react";
import RevealEffect from "@/components/effect/reveal-effect";
import ParallaxImage from "@/components/effect/parallax-image";

const AboutSection = () => {
  const featuresData = [
    {
      content: "2+ years experience with Frontend and Backend development",
    },
    { content: "Capable of effective teamwork and collaboration" },
    {
      content:
        "Continuously learning and improving skills to meet the industry's growing demands",
    },
    {
      content:
        "Experience in participating in multiple projects of varying scales and complexities",
    },
  ];

  const skillsData = [
    {
      href: "https://html5.spec.whatwg.org/",
      src: "/images/skills/html-5-svgrepo-com.svg",
      alt: "HTML5",
    },
    {
      href: "https://www.w3.org/TR/CSS/#css",
      src: "/images/skills/css3-svgrepo-com.svg",
      alt: "CSS3",
    },
    {
      href: "https://developer.mozilla.org/en-US/docs/Web/JavaScript",
      src: "/images/skills/js-svgrepo-com.svg",
      alt: "Javascript",
    },
    {
      href: "https://www.java.com/en/",
      src: "/images/skills/java-svgrepo-com.svg",
      alt: "JAVA",
    },
    {
      href: "https://www.typescriptlang.org/",
      src: "/images/skills/typescript-official-svgrepo-com.svg",
      alt: "Typescript",
    },
    {
      href: "https://reactjs.org/",
      src: "/images/skills/react-svgrepo-com.svg",
      alt: "React",
    },
    {
      href: "https://nextjs.org/docs",
      src: "/images/skills/nextjs-fill-svgrepo-com.svg",
      alt: "NextJs",
    },
    {
      href: "https://www.spring.io/",
      src: "/images/skills/spring-svgrepo-com.svg",
      alt: "Spring",
    },
    {
      href: "https://jquery.com/",
      src: "/images/skills/jquery-svgrepo-com.svg",
      alt: "JQuery",
    },

    {
      href: "https://sass-lang.com/",
      src: "/images/skills/sass-svgrepo-com.svg",
      alt: "Sass",
    },
    {
      href: "https://tailwindcss.com/",
      src: "/images/skills/tailwind-svgrepo-com.svg",
      alt: "TailwindCSS",
    },
    {
      href: "https://getbootstrap.com/",
      src: "/images/skills/bootstrap-svgrepo-com.svg",
      alt: "Bootstrap",
    },

    {
      href: "https://nodejs.org/en/",
      src: "/images/skills/nodejs-1-logo-svgrepo-com.svg",
      alt: "NodeJS",
    },
    {
      href: "https://www.mysql.com/",
      src: "/images/skills/mysql-logo-svgrepo-com.svg",
      alt: "MySQL",
    },
    {
      href: "https://www.mongodb.com/",
      src: "/images/skills/mongo-svgrepo-com.svg",
      alt: "MongoDB",
    },

    {
      href: "https://www.figma.com/",
      src: "/images/skills/figma-svgrepo-com.svg",
      alt: "Figma",
    },
    {
      href: "https://www.docker.com/",
      src: "/images/skills/docker-svgrepo-com.svg",
      alt: "Docker",
    },
    {
      href: "https://www.nginx.org/",
      src: "/images/skills/nginx-logo-svgrepo-com.svg",
      alt: "Nginx",
    },
  ];

  return (
    <section id="about" className="pt-16 xl:pt-32">
      <div className="container space-y-10">
        <h2 className="text-white text-xl sm:text-2xl 2md:text-3xl lg:text-4xl 2xl:text-[42px] 3xl:text-5xl font-semibold">
          About Me
        </h2>
        <div className="border-l-[2px] border-neutral-800 ps-6 xl:ps-10 text-neutral-400 text-base lg:text-lg space-y-4 xl:space-y-8">
          <RevealEffect>
            <p className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg">
              I am a Web Developer with 2+ years of experience. I specialize in
              solving UI/UX challenges and creating optimal features for users.
              My forte lies in transforming complex ideas into intuitive,
              user-friendly interfaces. Beyond work, I regularly explore new
              technologies and industry best practices to enhance my skills. I
              &apos;m particularly interested in website performance
              optimization, implementing responsive design, and ensuring
              accessibility for all user groups. My goal is to become a
              well-rounded software engineer, always up-to-date with modern web
              development trends.
            </p>
          </RevealEffect>
          <div>
            <h3 className="text-white text-base sm:text-xl 2md:2xl 2xl:text-3xl font-semibold">
              Features
            </h3>
            <ul className="ps-2 md:ps-4 xl:ps-10">
              {featuresData.map((item, index) => {
                return (
                  <li key={index}>
                    <RevealEffect>
                      <div className="flex gap-x-2 md:gap-x-4 xl:gap-x-10 items-center">
                        <div>
                          <Dot
                            size={48}
                            strokeWidth={2}
                            className="text-blue-500"
                          />
                        </div>
                        <p className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg">
                          {item.content}
                        </p>
                      </div>
                    </RevealEffect>
                  </li>
                );
              })}
            </ul>
          </div>
          <div className="space-y-6">
            <h3 className="text-white text-base sm:text-xl 2md:2xl 2xl:text-3xl font-semibold">
              Skills
            </h3>
            <p className="flex gap-10 flex-wrap">
              {skillsData.map((skill, index) => (
                <a
                  key={index}
                  href={skill.href}
                  rel="nofollow"
                  className="hover:scale-105 transition-all duration-150 p-3 bg-white rounded-full"
                >
                  <ParallaxImage
                    src={skill.src}
                    width={35}
                    height={35}
                    alt={skill.alt}
                    reverse={index % 2 === 0}
                  />
                </a>
              ))}
            </p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AboutSection;
