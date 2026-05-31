package com.foreverlost.main;

/**
 * Tracks items and equipment the player has collected during the mission.
 */
public class Player {
    private boolean l2Keycard;
    private boolean l4Keycard;
    private boolean labCoat;
    private boolean mug;
    private boolean screwdriver;
    private boolean enteredSecurityRoomViaVent;

    /**
     * Returns true if the player has obtained the level 2 keycard.
     * @return boolean
     */
    public boolean hasL2Keycard() {
        return l2Keycard;
    }

    /**
     * Sets the l2Keycard value to true.
     * There should be no reason to set this to false.
     */
    public void setL2KeycardTrue() {
        this.l2Keycard = true;
    }

    /**
     * Returns true if the player has obtained the level 4 keycard.
     * @return boolean
     */
    public boolean hasL4Keycard() {
        return l4Keycard;
    }

    /**
     * Sets the l4Keycard value to true. There should be no reason to set this to false.
     */
    public void setL4KeycardTrue() {
        this.l4Keycard = true;
    }

    /**
     * Checks if the player has the lab coat.
     * @return boolean
     */
    public boolean hasLabCoat() {
        return labCoat;
    }

    /**
     * Sets the lab coat value to true. There should be no reason to set this to false.
     * @param labCoat
     */
    public void setLabCoatTrue() {
        this.labCoat = true;
    }

    /**
     * Checks if the player has the mug.
     * @return boolean
     */
    public boolean hasMug() {
        return mug;
    }

    /**
     * Sets the mug value to true. There should be no reason to set this to false.
     * @param mug
     */
    public void setMugTrue() {
        this.mug = true;
    }

    /**
     * Checks if the player has the screwdriver.
     * @return boolean
     */
    public boolean hasScrewdriver() {
        return screwdriver;
    }

    /**
     * Sets the screwdriver value to true. There should be no reason to set this to false.
     */
    public void setScrewdriverTrue() {
        this.screwdriver = true;
    }

    /**
     * Returns true if the player has entered the security room via the vent.
     * @return boolean
     */
    public boolean enteredSecurityRoomViaVent() {
        return enteredSecurityRoomViaVent;
    }

    /** Setter for the enteredSecurityRoomViaVent field*/
    public void setEnteredSecurityRoomViaVent(boolean enteredSecurityRoomViaVent) {
        this.enteredSecurityRoomViaVent = enteredSecurityRoomViaVent;
    }
}
