
LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, ./src)
LOCAL_STATIC_JAVA_LIBRARIES := android_common_fce define_fce

LOCAL_MODULE_INCLUDE_LIBRARY := true

LOCAL_PACKAGE_NAME := Launcher_xmc

LOCAL_CERTIFICATE := shared

LOCAL_PRIVILEGED_MODULE := true

LOCAL_OVERRIDES_PACKAGES := Home

LOCAL_MULTILIB := both

LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

include $(CLEAR_VARS)
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := android_common_fce:libs/android_common.jar \
                                        define_fce:libs/define.jar
    
include $(BUILD_MULTI_PREBUILT)

include $(call all-makefiles-under,$(LOCAL_PATH))
