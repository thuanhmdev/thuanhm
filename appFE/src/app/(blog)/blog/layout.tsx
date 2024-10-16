import { Metadata, ResolvingMetadata } from "next";
import React from "react";
import AuthUser from "./auth-user";
import Header from "@/components/header";
import Footer from "@/components/footer";
import { sendRequest } from "@/http/http";

export async function generateMetadata(): Promise<Metadata> {

  let metadata = {
    title: "Trang chủ",
    description: "",
    openGraph: {
      locale: "vi_VN",
      siteName: "ThuanHM",
    },
  };

  try {
    const result = await sendRequest<TResponse<TSetting>>({
      url: `/blog-api/settings`,
      method: "GET",
    });
    if (result.statusCode === 200) {
      metadata = {
        title: result.data.title,
        description: result.data.description,
        openGraph: {
          siteName: result.data.siteName,
          locale: "vi_VN",
        },
      };
    }
  } catch (error) {
    console.error("Error in generateMetadata:", error);
  } finally {
    console.log("Metadata generation completed");
  }
  return metadata;
}

const ClientLayout = ({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) => {
  return (
    <>
      <Header />
      <AuthUser />
      <div className="min-h-[90vh]">{children}</div>
      <Footer />
    </>
  );
};

export default ClientLayout;
