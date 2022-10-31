FROM gitpod/workspace-full-vnc

USER root

RUN apt-get update \
    && apt-get install -y libgtk-3-dev openjfx libopenjfx-java matchbox libgl1-mesa-glx \
    && apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*

FROM gitpod/workspace-full

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 17.0.3-ms && \
    sdk default java 17.0.3-ms"
