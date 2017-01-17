# Copyright (C) 2016 The CyanogenMod Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Get the prebuilt list of APNs
$(call inherit-product, vendor/omni/config/gsm.mk)

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/aosp_base_telephony.mk)

# must be before including omni part
TARGET_BOOTANIMATION_SIZE := 640x362

# Inherit from our custom product configuration
$(call inherit-product, vendor/omni/config/common.mk)

# Inherit from onyx device
$(call inherit-product, device/oneplus/onyx/device.mk)

# Call the proprietary setup
$(call inherit-product-if-exists, vendor/oneplus/onyx/onyx-vendor.mk)

# Must define platform variant before including any common things
TARGET_BOARD_PLATFORM_VARIANT := msm8974

PRODUCT_NAME := omni_onyx
PRODUCT_DEVICE := onyx
PRODUCT_MANUFACTURER := OnePlus

PRODUCT_GMS_CLIENTID_BASE := android-oneplus

PRODUCT_BRAND := OnePlus
TARGET_VENDOR := oneplus

TARGET_VENDOR_PRODUCT_NAME := onyx
TARGET_VENDOR_DEVICE_NAME := OnePlus

PRODUCT_BUILD_PROP_OVERRIDES += \
    TARGET_DEVICE=OnePlus \
    PRODUCT_NAME=OnePlus

PRODUCT_BUILD_PROP_OVERRIDES += \
    BUILD_FINGERPRINT=OnePlus/OnePlus/OnePlus:5.1.1/LMY47V/1441677661:user/release-keys \
    PRIVATE_BUILD_DESC="OnePlus"

PRODUCT_SYSTEM_PROPERTY_BLACKLIST += ro.product.model
