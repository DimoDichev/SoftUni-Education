class Laptop {
    constructor(info, quality) {
        this.info = info;
        this.isOn = Boolean(false);
        this.quality = quality;
    }

    turnOn() {
        this.isOn = Boolean(true);
        this.quality-=1;
    }

    turnOff() {
        this.isOn = Boolean(false);
        this.quality-=1;
    }

    showInfo() {
        return JSON.stringify(this.info);
    }

    get price() {
        return Number(800 - (this.info.age * 2) + (this.quality * 0.5));
    }
}