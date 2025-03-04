import SmoothScroll from "@/components/effect/smooth-scroll";
import Footer from "@/components/footer";
import Header from "@/components/header";
import MouseLight from "@/components/mouse-light";
import AboutSection from "@/components/section/about-section";
import HomeSection from "@/components/section/home-section";
import PersonalProjectSection from "@/components/section/project-section";

const HomePage = () => {
    return (
        <div className="bg-background ">
            <MouseLight />
            <SmoothScroll>
                <HomeSection />
                <AboutSection />
                <PersonalProjectSection />
            </SmoothScroll>
        </div>
    );
};

export default HomePage;