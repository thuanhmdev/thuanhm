import { Dot } from "lucide-react";
import React from "react";
import RevealEffect from "@/components/effect/reveal-effect";
import ParallaxImage from "@/components/effect/parallax-image";

const AboutSection = () => {
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
          <div>
            <h3 className="text-white text-base sm:text-xl 2md:2xl 2xl:text-3xl font-semibold py-2">
              Summary
            </h3>
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
          </div>
          <div>
            <h3 className="text-white text-base sm:text-xl 2md:2xl 2xl:text-3xl font-semibold py-2">
              Education
            </h3>
            <RevealEffect>
              <>
                <h5 className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg font-bold">
                  🚧 2018 - 2022 | CAN THO UNIVERSITY
                </h5>
                <p>
                  Bachelor of Software Engineering
                </p>
              </>
            </RevealEffect>
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
                    width={30}
                    height={30}
                    alt={skill.alt}
                    reverse={index % 2 === 0}
                  />
                </a>
              ))}
            </p>
          </div>
          <div>
            <h3 className="text-white text-base sm:text-xl 2md:2xl 2xl:text-3xl font-semibold py-2">
              Work History
            </h3>
            <RevealEffect>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6 md:gap-10">
                <div className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg ">
                  <h5 className="font-bold">
                    🚧 2023 - 2024 | APAC TECH
                  </h5>

                  <p>
                    I am a Web Developer. Worked with many languages ​​such as Javascript, PHP, Java, ReactJS, Spring Framework, Laravel. Have done lot of
                    web projects
                  </p>
                </div>
                <div className="leading-7 2xl:leading-8 text-neutral-400 text-xs sm:text-sm lg:text-base 2xl:text-lg ">
                  <h5 className="font-bold">
                    🚧 2022 - 2023 | MOR SOFTWARE
                  </h5>

                  <p>
                    I am a Frontend Developer. Collaborate with team members to implement user interfaces
                  </p>
                </div>
              </div>
            </RevealEffect>
          </div>
        </div>
      </div>
    </section >
  );
};

export default AboutSection;
