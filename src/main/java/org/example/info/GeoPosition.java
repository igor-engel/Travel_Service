package org.example.info;

public class GeoPosition {
    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        try {
            this.latitude = parseDouble(latitudeGradus);
            this.longitude = parseDouble(longitudeGradus);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Переданы некорректные параметры. Используйте формат 55, 55(45'07''), 59(57'00'')");
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private double parseDouble(String string) {
        if ((string.indexOf('(') >= 0)) {
            if (string.charAt(0) == '-') {
                double result = Double.parseDouble(string.substring(0, string.indexOf('(')));
                result = result - Double.parseDouble(string.substring(string.indexOf('(') + 1, string.indexOf('\''))) / 60;
                result = result - Double.parseDouble(string.substring(string.indexOf('\'') + 1, string.length() - 3)) / 3600;

                return Math.toRadians(result);
            }

            double result = Double.parseDouble(string.substring(0, string.indexOf('(')));
            result = result + Double.parseDouble(string.substring(string.indexOf('(') + 1, string.indexOf('\''))) / 60;
            result = result + Double.parseDouble(string.substring(string.indexOf('\'') + 1, string.length() - 3)) / 3600;

            return Math.toRadians(result);
        }
        return Math.toRadians(Double.parseDouble(string));
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
