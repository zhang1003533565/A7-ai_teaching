import request from './request'

/**
 * 上传视频
 * @param {File} file - 视频文件
 * @returns {Promise} 上传结果
 */
export function uploadVideo(file) {
    const formData = new FormData();
    formData.append('file', file);
    return request({
        url: '/videos/upload',
        method: 'post',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        },
        // 上传进度处理
        onUploadProgress: progressEvent => {
            const percentCompleted = Math.round((progressEvent.loaded * 100) / progressEvent.total);
            console.log('上传进度：', percentCompleted);
        }
    });
}

/**
 * 获取视频列表
 * @returns {Promise} 视频列表
 */
export function getVideoList() {
    return request({
        url: '/videos/list',
        method: 'get'
    });
}

/**
 * 删除视频
 * @param {string} filename - 视频文件名
 * @returns {Promise} 删除结果
 */
export function deleteVideo(filename) {
    return request({
        url: `/videos/${filename}`,
        method: 'delete'
    });
}

/**
 * 获取视频流式播放链接
 * @param {string} filename - 视频文件名
 * @returns {string} 播放链接
 */
export function getVideoStreamUrl(filename) {
    return `${request.defaults.baseURL}/videos/stream/${filename}`;
}

/**
 * 获取视频下载链接
 * @param {string} filename - 视频文件名
 * @returns {string} 下载链接
 */
export function getVideoDownloadUrl(filename) {
    return `${request.defaults.baseURL}/videos/download/${filename}`;
} 