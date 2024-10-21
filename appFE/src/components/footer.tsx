"use client";
import { sendRequest } from "@/http/http";
import { usePathname } from "next/navigation";
import { useEffect, useState } from "react";

const Footer = () => {
  const pathname = usePathname();
  const isBlogPage = pathname.startsWith("/blog");

  const [siteName, setSiteName] = useState("");

  const handleCallSetting = async () => {
    const setting = await sendRequest<TResponse<TSetting>>({
      url: `/blog-api/settings`,
      method: "GET",
    });
    if (setting.statusCode === 200) {
      setSiteName(setting.data.siteName);
    }
  };

  useEffect(() => {
    handleCallSetting();
  }, []);

  return (
    <>
      <footer
        id="footer"
        className={` py-6 ${
          isBlogPage ? "bg-white text-black" : "bg-background text-neutral-400"
        }`}
      >
        <div className="container">
          <p className=" text-sm md:text-sm flex  justify-center gap-x-2">
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
