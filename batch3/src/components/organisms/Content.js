import { useEffect, useState } from "react";
import ThemeService from "../../services/ThemeService";

function Content() {

    const [theme, setTheme] = useState(ThemeService.getTheme());

    useEffect(() => {

        const observer = (newTheme) => {
            setTheme(newTheme);
        };

        ThemeService.attach(observer);

        return () => ThemeService.detach(observer);

    }, []);

    const style = {
        padding: "20px",
        margin: "20px",
        backgroundColor: theme === "Light" ? "#ffffff" : "#333333",
        color: theme === "Light" ? "#000000" : "#ffffff"
    };

    return (
        <div style={style}>
            <h2>Content Section</h2>
            <p>The theme is {theme}.</p>
        </div>
    );
}

export default Content;