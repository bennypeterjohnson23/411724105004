class ThemeService {
    constructor() {
        this.theme = "Light";
        this.observers = [];
    }

    attach(observer) {
        this.observers.push(observer);
    }

    detach(observer) {
        this.observers = this.observers.filter(obs => obs !== observer);
    }

    notify() {
        this.observers.forEach(observer => observer(this.theme));
    }

    toggleTheme() {
        this.theme = this.theme === "Light" ? "Dark" : "Light";
        this.notify();
    }

    getTheme() {
        return this.theme;
    }
}

export default new ThemeService();