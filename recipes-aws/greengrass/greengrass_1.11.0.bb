require greengrass.inc

LICENSE = "MIT"
LIC_FILES_CHKSUM = " \
    file://ggc/core/THIRD-PARTY-LICENSES;md5=d3fb176f85edb203d99ed157c1301989 \
"

SRC_URI_arm = " \
    https://d1onfpft10uf5o.cloudfront.net/greengrass-core/downloads/${PV}/greengrass-linux-armv7l-${PV}.tar.gz;name=arm \
    file://greengrass.service \
    file://greengrass-init \
    file://config_secu_example.json \
    file://tpm_update.sh \
"


SRC_URI[arm.md5sum]        = "c5f2981d724e200c0d68ee41e6f6b47c"
SRC_URI[arm.sha256sum]     = "af6ac0b277193a17d59b010071e153aa3d9aca1136062dd044caab3a9b663b13"


# Release specific configuration

do_install_append() {
  install -m 0644 ${WORKDIR}/config_secu_example.json ${D}/greengrass/config/config_secu_example.json
  install -m 0644 ${WORKDIR}/tpm_update.sh ${D}/greengrass/tpm_update.sh
}

RDEPENDS_${PN} += "ca-certificates python3-json python3-numbers sqlite3"
RDEPENDS_${PN} += "opensc openssl libp11"

INSANE_SKIP_${PN} += " libdir"

FETCHCMD_wget = "/usr/bin/env wget -t 10 -T 30 --passive-ftp --no-check-certificate"
