FROM gitpod/workspace-full-vnc

USER gitpod

RUN sudo apt-get update && \
    sudo apt-get install -y libgtk-3-dev && \
    sudo apt-get install -y libgl1 && \
    sudo apt-get install -y libgl1-mesa-glx && \
    sudo apt-get install -y libgl1-mesa-dev xorg-dev && \
    sudo apt-get install -y openjfx libopenjfx-java matchbox && \
    sudo apt-get clean && sudo rm -rf /var/cache/apt/* && sudo rm -rf /var/lib/apt/lists/* && sudo rm -rf /tmp/*

FROM gitpod/workspace-full

USER gitpod
#
# RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
#     sdk install java 15.0.1.fx-zulu && \
#     sdk default java 15.0.1.fx-zulu"

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
    sdk install java 17.0.3-ms && \
    sdk default java 17.0.3-ms"