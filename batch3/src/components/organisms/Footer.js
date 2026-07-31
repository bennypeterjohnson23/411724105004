import { useEffect, useState } from "react";
import ThemeService from "../../services/ThemeService";

function Footer() {

    const [theme, setTheme] = useState(ThemeService.getTheme());

    useEffect(() => {

        const observer = (newTheme) => {
            setTheme(newTheme);
        };

        ThemeService.attach(observer);

        return () => ThemeService.detach(observer);

    }, []);

    return (
        <footer>
            Footer Theme: {theme}
        </footer>
    );
}

export default Footer;