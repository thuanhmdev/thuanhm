"use client";
import { sendRequest } from "@/http/http";
import { usePathname } from "next/navigation";
import { useEffect, useState } from "react";

const Footer = () => {

  const [siteName, setSiteName] = useState("");
  const pathname = usePathname();
  const isProfilePage = pathname.startsWith("/my-profile");

  const [classNameProfilePage, setClassNameProfilePage] = useState(() => {
    return isProfilePage ? "bg-background text-neutral-200" : "bg-white text-black"
  })

  useEffect(() => {
    setClassNameProfilePage(() => isProfilePage ? "bg-background text-neutral-200" : "bg-white text-black")
  }, [pathname])


  const handleCallSetting = async () => {
    const setting = await sendRequest<TResponse<TSetting>>({
      url: `/blog-api/setting`,
      method: "GET",
    });
    if (setting.status === 200) {
      setSiteName(setting.data.siteName);
    }
  };

  useEffect(() => {
    handleCallSetting();
  }, []);

  if (pathname.startsWith("/admin")) {
    return <></>
  }

  return (
    <>
      <footer
        id="footer"
        className={`py-6 ${classNameProfilePage}
        `}
      >
        <div className="container">
          <p className="text-sm md:text-sm flex  justify-center gap-x-2">
            Copyright {new Date().getFullYear()}
            <span className="font-semibold">{siteName}</span>
            All Rights Reserved
          </p>
        </div>
      </footer>
    </>
  );
};

export default Footer;
