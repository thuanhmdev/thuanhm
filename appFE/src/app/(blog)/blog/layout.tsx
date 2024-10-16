import { Metadata, ResolvingMetadata } from "next";
import React from "react";
import AuthUser from "./auth-user";
import Header from "@/components/header";
import Footer from "@/components/footer";

export async function generateMetadata(
  parent: ResolvingMetadata
): Promise<Metadata> {
  return {
    title: (await parent).title,
    description: (await parent).description,
    openGraph: {
      siteName: (await parent)?.openGraph?.siteName,
      locale: (await parent)?.openGraph?.locale,
    },
  };
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
