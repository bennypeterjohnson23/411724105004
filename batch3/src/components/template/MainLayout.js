import Header from "../organisms/Header";
import ThemePanel from "../organisms/ThemePanel";
import Content from "../organisms/Content";
import Footer from "../organisms/Footer";

function MainLayout() {

    return (
        <div>

            <Header />

            <ThemePanel />

            <Content />

            <Footer />

        </div>
    );
}

export default MainLayout;