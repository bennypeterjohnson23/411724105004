import Text from "../atoms/Text";

function ThemeLabel({ theme }) {
    return (
        <Text>
            Current Theme: {theme}
        </Text>
    );
}

export default ThemeLabel;