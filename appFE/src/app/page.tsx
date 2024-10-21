import MouseLight from "@/components/mouse-light";
import AboutSection from "@/components/section/about-section";
import HomeSection from "@/components/section/home-section";
import PersonalProjectSection from "@/components/section/personal-project-section";
import WorkSection from "@/components/section/work-section";
import SmoothScroll from "@/components/effect/smooth-scroll";
import Header from "@/components/header";
import Footer from "@/components/footer";

const HomePage = () => {
  return (
    <div className="bg-background ">
      <MouseLight />

      <SmoothScroll>
        <Header />
        <HomeSection />
        <AboutSection />
        <WorkSection />
        <PersonalProjectSection />
        <Footer />
      </SmoothScroll>
    </div>
  );
};

export default HomePage;
