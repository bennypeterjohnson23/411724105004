import { useEffect, useState } from "react";
import ThemeService from "../../services/ThemeService";
import ThemeLabel from "../molecule/ThemeLabel";
import Button from "../atoms/Button";

function ThemePanel() {

    const [theme, setTheme] = useState(ThemeService.getTheme());

    useEffect(() => {

        const observer = (newTheme) => {
            setTheme(newTheme);
        };

        ThemeService.attach(observer);

        return () => ThemeService.detach(observer);

    }, []);

    return (
        <div>

            <ThemeLabel theme={theme} />

            <Button
                text="Toggle Theme"
                onClick={() => ThemeService.toggleTheme()}
            />

        </div>
    );
}

export default ThemePanel;