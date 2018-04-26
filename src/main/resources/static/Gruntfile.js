module.exports = function(grunt){
	'use strict'
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		uglify: {
			options:{
				mangle: true,
				beautify: true,
				stripBanners: true,
				banner: '/*<%=pkg.name%>-<%=pkg.version%>@<%=grunt.template.today("yyyy-mm-dd")%>*/\n'
			},
			production: {
				files:{
					'dist/js/uep.min.js':['dist/js/uep.js',
				'dist/js/const.js',
				'dist/js/eventList.js',
				'dist/js/evetView.js',
				'dist/js/eventEdit.js'
				]
				}
			}
		}

	});
	grunt.loadNpmTasks("grunt-contrib-uglify");
	grunt.loadNpmTasks("grunt-contrib-jshint");
	grunt.registerTask('default', []);

	
	grunt.registerTask('prod', ['uglify']);
};