/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: "https",
        hostname: "thuanhm.com",
        pathname: "/**",
      },
      {
        protocol: "http",
        hostname: "localhost",
        port: "8080",
        pathname: "/**",
      },
      {
        protocol: "http",
        hostname: "host.docker.internal",
        pathname: "/**",
      },
      {
        protocol: "https",
        hostname: "lh3.googleusercontent.com",
        pathname: "/**",
      },
      {
        protocol: "https",
        hostname: "avatars.githubusercontent.com",
        pathname: "/**",
      },
      {
        protocol: "https",
        hostname: "raw.githubusercontent.com",
        pathname: "/**",
      },
    
    ],
  },
  reactStrictMode: false,

};

export default nextConfig;
