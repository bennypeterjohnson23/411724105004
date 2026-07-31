import { useEffect, useState } from "react";
import ThemeService from "../../services/ThemeService";

function Header() {

    const [theme, setTheme] = useState(ThemeService.getTheme());

    useEffect(() => {

        const observer = (newTheme) => {
            setTheme(newTheme);
        };

        ThemeService.attach(observer);

        return () => ThemeService.detach(observer);

    }, []);

    return (
        <header>
            <h1>Header - {theme} Theme</h1>
        </header>
    );
}

export default Header;