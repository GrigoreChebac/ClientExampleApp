package com.example.LightsControllerService;

import com.example.LightsControllerService.LedColor;

/**
 * This AIDL service is hosted in the platform lights controller service and the AIDL interface
 * file should be kept in sync with the one located in that component.
 */
interface ILightsControllerService {

    /**
     * Override the default LED light behavior and instructs the device to light its LEDs with the specified
     * color.  To turn LED lights off, set color to {@link LedColor#BLACK}.
     * <br />
     * LEDs will remain at the set color until changed by the AIDL client, or until reset() is called.
     * That is, calling this method effectively overrides the LED state machine within this service.
     *
     * @param color The color to set the LED lights to
     * @return True if successful
     */
    boolean setLedColor(in LedColor color);
}
