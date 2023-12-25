//def repo = 'https://github.com/LovelyCatEx/shinkai-backend'
def repo = 'https://gitee.com/lovelycatv/shinkai-backend'
def module_name = "shinkai-backend"
def APP_BRANCH = "master"

node {
    stage('Delete Workspace') {
        echo "清理工作目录"
        deleteDir()
    }

    stage('Pull Repository') {
        echo "正在从仓库拉取代码, 分支: ${APP_BRANCH}"
        checkout([$class: 'GitSCM',
                  branches: [[name: "*/${APP_BRANCH}"]],
                  extensions: [],
                  userRemoteConfigs: [[url: "${repo}"]]])
        echo "拉取代码完成"
    }


    stage('Maven Build') {
        echo "准备构建${module_name}"
        withMaven(globalMavenSettingsConfig: '', jdk: 'jdk17', maven: 'maven', mavenSettingsConfig: '', traceability: true) {
            sh "mvn -f ./ clean package -Dpmd.skip=true -Dcheckstyle.skip=true"

        }
    }

    stage('SSH Publish') {
        sshPublisher(publishers: [
                sshPublisherDesc(
                        configName: 'aliyun_2',
                        transfers: [
                                sshTransfer(
                                        cleanRemote: false,
                                        excludes: '',
                                        execCommand: 'cd jenkins_result/shinkai/ && sh startup.sh',
                                        execTimeout: 120000,
                                        flatten: false,
                                        makeEmptyDirs: false,
                                        noDefaultExcludes: false,
                                        patternSeparator: '[, ]+',
                                        remoteDirectory: 'jenkins_result/shinkai/',
                                        remoteDirectorySDF: false,
                                        removePrefix: 'target/',
                                        sourceFiles: 'target/shinkai-backend-0.0.1-SNAPSHOT.jar')
                        ],
                        usePromotionTimestamp: false,
                        useWorkspaceInPromotion: false,
                        verbose: true
                )])
    }


}