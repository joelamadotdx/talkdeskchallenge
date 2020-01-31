var gulp = require('gulp');
var fs = require('fs');
var apiConverter = require('api-spec-converter');

gulp.task('default', function () {
    return apiConverter.convert({
        from: 'openapi_3',
        to: 'swagger_2',
        source: 'target/classes/META-INF/openapi.json'
    }, function (err, converted) {
        !fs.existsSync('target/generated/') && fs.mkdirSync('target/generated/');
        fs.writeFileSync('target/generated/swagger2.yaml', converted.stringify({syntax: 'yaml'}));
    })
});